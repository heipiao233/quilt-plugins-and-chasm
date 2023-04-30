# 第一个插件
## 在一切之前
我们有了一个好的开发环境。然而这还不够，因为加载器插件属于 Quilt 加载器的实验性功能，所以，我们需要先通过 JVM 参数启用它们。
在开发环境，可以在 build.gradle 加入这些内容：
```groovy
loom {
    runs.all {
        property("loader.experimental.allow_loading_plugins", "true")
    }
}
```
事实上，这将在开发环境运行时加入一个 JVM 参数：`-Dloader.experimental.allow_loading_plugins=true`。
如果没有它，Quilt 加载器将会在检测到插件时报错。
## 开始编写
我们先在模组里加入一个包 `plugin`，然后在包里增加一个类 `QPACPlugin`。这就是我们插件的主类，它要实现 `QuiltLoaderPlugin` 接口。

这个接口里有两个方法必须实现：`load` 和 `unload`。这里，我们向日志分别输出两行字：

`Hello from QPAC plugin!`

`Goodbye from QPAC plugin!`

显然可以直接
