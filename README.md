# iaac4j.aliyun
iaac4j.aliyun是为了简化在阿里云上实现IaaC(基础设施即是代码)的提供基础组件。
iaac4j.aliyun对[阿里云资源编排(ROS)API](https://help.aliyun.com/document_detail/28899.html?spm=5176.doc28898.6.569.iuCpkK),进行了高层抽象和封装大大简化阿里云ROS API的调用。同时提供`git`和`http`拉取资源编排模板的能力。

##特性
* 基于阿里云ROS原生API做了高层抽象和封装，做到极致简化，大部分功能一行代码搞定
* 完整实现了所有阿里云原生ROS API的功能[原生API接口](https://help.aliyun.com/document_detail/28899.html?spm=5176.doc28898.6.569.iuCpkK)
* 增强了阿里云原生ROS API，增加了通过名字对资源栈的操作的能力。
* 对资源编排模板和参数文件提供了抽象，支持git和http拉取资源文件
* 返回原生Map，对基于jvm的脚本语言，如Groovy的友好支持
* 线程安全

##典型应用场景
###快速实现CDI（基础设施的持续交付）
基础设施也可以像应用一样实施持续交付，**CDI(Continuous Delivery Infrastructure)**是一种实现敏捷化基础设施的方法。

ROS模板存放在Git上，基于iaac4j.aliyun开发一个接收url请求的服务或通过Jenkins调用iaac4j.aliyun，在Git上发送webhook触发资源栈的创建和更新。企业所有的it资源都可以通过上述流程来管理，实现流水线交付基础设施。

###在上层PaaS内扩容/缩容下层基础设施

###定时开启和释放基础设置



