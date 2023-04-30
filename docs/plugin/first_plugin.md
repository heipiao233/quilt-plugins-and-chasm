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

显然可以直接在 `load` 和 `unload` 当中加入日志代码。最终代码如下：
```java
package net.heipiao.qpac.plugin;

import org.quiltmc.loader.api.LoaderValue;
import org.quiltmc.loader.api.plugin.QuiltLoaderPlugin;
import org.quiltmc.loader.api.plugin.QuiltPluginContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class QPACPlugin implements QuiltLoaderPlugin {
	public static final Logger LOGGER = LoggerFactory.getLogger("QPAC Plugin");
    
	@Override
	public void load(QuiltPluginContext context, Map<String, LoaderValue> previousData) {
		LOGGER.info("Hello from QPAC plugin!");
	}

	@Override
	public void unload(Map<String, LoaderValue> data) {
		LOGGER.info("Goodbye from QPAC plugin!");
	}
}
```
然而光光这一个类，Quilt 加载器是绝不可能识别到的。因此，需要在 `quilt.mod.json` 的根对象中加入 `experimental_quilt_loader_plugin` 字段。
该字段内容应如下
```json
{
    "class": "net.heipiao.qpac.plugin.QPACPlugin",
    "packages": [
        "net.heipiao.qpac.plugin"
    ]
}
```
`class` 表示我们插件的类名，`packages` 表示我们插件可以直接访问的类，不经过任何处理，例如 Mixin、AW 和 Chasm。

启动游戏，提示”错误“和我们的日志：
```
[12:56:48] [main/INFO] (Quilt Loader/GameProvider) Loading Minecraft 1.19.4 with Quilt Loader 0.19.0-beta.13
[12:56:48] [main/ERROR] (Quilt Loader) MOD qpac PROVIDES A PLUGIN!MOD-PROVIDED PLUGINS ARE FOR AMUSEMENT PURPOSES ONLY. NO WARRANTY IS PROVIDED, EXPRESS OR IMPLIED. CONTINUE AT YOUR OWN RISK.
2023-04-30 12:56:49,398 main WARN Error parsing URI E:\quilt-plugins-and-chasm\.gradle\quilt-loom-cache\log4j.xml
[12:56:49] [main/INFO] (QPAC Plugin) Hello from QPAC plugin!
```
可以看到，插件的加载时间是非常早的。实际上，它在 `runInternal` 的第二轮循环开始时就运行了 （第一轮只运行内置插件）。

这些代码在仓库的 `first_plugin` tag 里。
