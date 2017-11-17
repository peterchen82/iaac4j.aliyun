# iaac4j.aliyun
iaac4j.aliyun是为了简化在阿里云上实现IaaC(基础设施即是代码)的提供基础组件。
iaac4j.aliyun对[阿里云资源编排(ROS)API](https://help.aliyun.com/document_detail/28899.html?spm=5176.doc28898.6.569.iuCpkK),进行了高层抽象和封装大大简化阿里云ROS API的调用。同时提供`git`和`http`拉取资源编排模板的能力。

## 特性
* 基于阿里云ROS原生API做了高层抽象和封装，做到极致简化，大部分功能一行代码搞定
* 完整实现了所有阿里云原生ROS API的功能[原生API接口](https://help.aliyun.com/document_detail/28899.html?spm=5176.doc28898.6.569.iuCpkK)
* 增强了阿里云原生ROS API，增加了通过名字对资源栈的操作的能力。
* 对资源编排模板和参数文件提供了抽象，支持git和http拉取资源文件
* 实现PaaS/CaaS和阿里云IaaS环境的混合编排.
* 返回原生Map，对基于jvm的脚本语言，如Groovy的友好支持
* 线程安全

## 典型应用场景
### 快速实现CDI（基础设施的持续交付）
基础设施也可以像应用一样实施持续交付，**CDI(Continuous Delivery Infrastructure)** 是一种实现敏捷化基础设施的方法。

ROS模板存放在Git上，基于iaac4j.aliyun开发一个接收url请求的服务或通过Jenkins调用iaac4j.aliyun，在Git上发送webhook触发资源栈的创建和更新。企业所有的it资源都可以通过上述流程来管理，实现流水线交付基础设施。

### 在上层PaaS内扩容/缩容下层基础设施
在阿里云IaaS上层部署的PaaS或容器编排集群内的程序,可以根据判断各类度量指标调用iaac4j.aliyun完成对底层资源的扩容与缩容。

### 定时开启和释放基础设置
一些离线业务，如基于spark/hadoop的分析应用可以在运行任务之前调用iaac4j.aliyun开通相应的资源，在离线任务结束后在调用iaac4j.aliyun释放资源。整个过程完全自动化完成，相对于包年包月的业务可以大幅度减少成本。

## 开始使用iaac4j.aliyun
### 安装
#### maven:
`马上就来`
#### gradle:
`马上就来`
#### 编译
环境要求：java>=jdk1.8,gradle>=2.3

    1. git clone https://github.com/peterchen82/iaac4j.aliyun.git
    2. cd iaac4j.aliyun&&gradle assemble

### 初始化Stack API

    //定义鉴权信息
    private static final ApiCredential credential = new ApiCredential(ACCESS_KEY, ACCESS_SECRET);
    //创建资源栈对象
    private static final Stack stack = new Stack(Regions.CN_HANGZHOU, credential);
### API调用方式
iaac4j.aliyun完整实现了对资源栈/资源栈事件/资源栈模板/资源栈内资源的相关接口，请参考[阿里云ROS文档](https://help.aliyun.com/document_detail/28899.html?spm=5176.doc28928.6.569.p9Yiwk)

调用方式：

    stack.<操作>.<配置方法>.<配置方法...>.run();

如:

    //删除指定名字的资源栈
    stack.delete().name("stackName").run();
                
    
### 预览资源编排模板
预览模板可以根据传入模板的参数校验这个资源编排是否合法。

iaac4j.aliyun涉及模板操作的操作:`preview/create/update`支持从`本地文件`、`Url`、`Git`加载模板和模板参数文件。

iaac4j.aliyun把所有资源的读取行为抽象为`iaac.aliyun.resource.Loader`，你可以自己实现Loader接口定制自己的读取策略。

#### 加载本地文件预览模板
    
    Map<String, Object> response = stack.preview()
                .name("stackName")
                .loadTemplate(stack.fileLoader().location(“ros.json”))
                .loadParameters(stack.fileLoader().location("params.json"))
                .timeoutMins(30).run();
                
                
#### 加载远程Url预览模板 

     Map<String, Object> response = stack.preview()
                .name(stackName)
                .loadTemplate(stack.httpLoader().location(rosTplUrl))
                .loadParameters(stack.httpLoader().location(paramsUrl))
                .timeoutMins(30).run();.run();           
#### 加载git资源预览模板

     Map<String, Object> response = stack.preview()
                .name(stackName)
                .loadTemplate(stack.gitLoader().location(rosGitUrl,"spark/spark-ros.json"))
                .loadParameters(stack.gitLoader().location(paramsGitUrl,"spark/spark-params.json"))
                .timeoutMins(30).run();

#### 创建资源栈
通过git上的模板和参数文件创建资源栈，这样基础设施代码化后可以通过版本控制系统方便的管理，还可以对基础设施实施持续交付。

       Map<String, Object> response = stack.create()
                .name(stackName)
                .loadTemplate(stack.gitLoader().location(rosGitUrl,"spark/spark-ros.json"))
                .loadParameters(stack.gitLoader().location(paramsGitUrl,"spark/spark-params.json"))
                .timeoutMins(30).run();

#### 删除资源栈
通过资源栈名和资源栈id删除，需要提前知道stackId.在创建资源栈后会返回stackId,也可以通过FindOne查询stackId.
        
    stack.delete().name(stackName).id(stackId).run();
        
可以根据名字直接删除资源栈，iaac4j.aliyun会自动根据名字查找出对应的StackId完成删除.这样你不必在你的应用中保持一个StackId,可以保证你的应用无状态化.

    stack.delete().name(stackName).run();


#### 废除资源栈
废除只会删除Stack但是不会释放Stack内包含的资源

    stack.abandon().name(stackName).run();

#### 查询资源栈

    Map<String, Object> response = stack.findOne().name("spark").run();
    
#### 查询资源栈详情    

    Map<String, Object> response = stack.describe().name(stackName).run();
    
#### 更新资源栈  
通过git更新资源栈，在运行时重载参数文件中定义的参数。

            Map<String, Object> response = stack.update()
                .name(stackName)
                .loadTemplate(stack.gitLoader().location(rosGitUrl,"spark/spark-ros.json"))
                .loadParameters(stack.gitLoader().location(paramsGitUrl,"spark/spark-params.json"))
                .parameters("NumOfNodes", 10)  //重载参数，扩容节点数到10
                .timeoutMins(30)
                .run();

通过这个特性轻松实现Stack的扩容与缩容,例如可以在Kubernetes中运行一个Pod来获取度量指标信息，通过判断度量指标来扩容/缩容这个Kubernetes中的工作节点.
iaac4j.aliyun为PaaS/CaaS环境提供了操作底层阿里云IaaS资源的能力，基于iaac4j.aliyun可以实现PaaS/CaaS和阿里云IaaS环境的混合编排.

#### 获取资源栈列表
Java:

    Map<String, Object> response = stack.list().run();
        List<Map> stacks = (List<Map>) response.get("Stacks");
        for (Map stack : stacks) {
            System.out.println(stack.get("Name") + "(" + stack.get("Id") + ")");
        }
        
Groovy:

    def stacks = stack.list().run().Stacks;
    stacks.each{
      println "${it.Name}(${it.Id})"
    }
    
iaac4j.aliyun中所有的命令都返回Map结果,充分的利用各类脚本语言的语法糖.
 
#### 获取可用区列表

    Map<String, Object> response = stack.regions().run();
        List<Map> regions = (List<Map>) response.get("Regions");
        for (Map region : regions) {
            System.out.println(region);
        }
        
#### 获取Stack中的资源列表

    Map<String, Object> response = stack.resource().list()
                .stackName(stackName).stackId(stackId)
                .run();
        List<Map> resources = (List<Map>) response.get("List");
        for (Map resource : resources) {
            System.out.println(resource.get("PhysicalId") + ":" + resource.get("Name") + "(" + resource.get("Type") + ")");
        }
        
#### 获取Stack中资源的详细信息

     Map<String, Object> response = stack.resource().describe()
                .stackName(stackName).stackId(stackId).name("Master")
                .run();
        System.out.println(response);

#### 获取资源类型列表

    Map<String, Object> response = stack.resource().typeList().run();
        List<String> resources = (List<String>) response.get("ResourceTypes");
        for (String resource : resources) {
            System.out.println(resource);
        }
        
#### 获取资源类型详细信息

    Map<String, Object> response = stack.resource().typeDescribe().type("ALIYUN::ECS::Instance").run();
        System.out.println(response);
        
#### 获取资源类型的模板样例

     Map<String, Object> response = stack.resource().typeTemplate().type("ALIYUN::ECS::Instance").run();
        System.out.println(response);

#### 获取Stack的事件列表

         Map<String, Object> response = ros.event().list()
                .stackName(stackName).stackId(stackId)
                .status(Event.Status.COMPLETE)
                .type("ALIYUN::ROS::Stack")
                .resourceName(stackName)
                .run();
        
        List<Map> events = (List<Map>) response.get("Events");
        for (Map event : events) {
            System.out.println(event.get("ResourceName") + ":" + event.get("ResourceType") + "(" + event.get("Status") + ")");
        }
        
可以根据事件来判断Stack中的资源是否就绪

#### 校验资源编排模板的格式

    Map<String, Object> response = stack.template().validate()
                .template(stack.fileLoader().location(rosTpl).load()).run();
        System.out.println(response);

#### 查询资源编排模板的详情

    Map<String, Object> response = stack.template().describe()
                .stackName(stackName).stackId(stackId)
                .run();
        System.out.println(response);
     
## 后续计划
* 后续准备加入对Stack监测的支持，例如可以在一个Stack中所有的资源创建完成后发出异步通知
* 同时计划提供所有API的异步版本
    
            

