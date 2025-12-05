# 🔍 Auto Git Annotate

> 打开文件自动显示 Git Blame —— 轻量级 JetBrains 全家桶插件

[![JetBrains Plugin](https://img.shields.io/badge/JetBrains-Plugin-orange?logo=jetbrains)](https://plugins.jetbrains.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Gradle-green?logo=gradle)](build.gradle.kts)

## ✨ 功能特性

- 🚀 **自动触发** - 打开文件即自动执行 Git Annotate，无需任何操作
- 🎯 **全家桶支持** - 支持所有 JetBrains IDE（IntelliJ IDEA、PyCharm、WebStorm、GoLand、CLion、Rider、PhpStorm、RubyMine 等）
- ⚡ **轻量零配置** - 安装即用，无需任何配置
- 🔧 **版本兼容** - 支持 2023.3 ~ 2025.3 版本

## 📸 效果预览

打开任意 Git 仓库中的文件后，左侧会自动显示每一行的提交信息：

```
2025/9/25  Gu    │ 180 │     "agent_name": agent.agent_name
2025/9/25  Gu    │ 181 │     "agent_version": agent.agent_version
2025/9/25  Gu    │ 182 │ }
```

## 📦 安装方法

### 方式一：从 Releases 下载（推荐）

1. 前往 [Releases](https://github.com/WyRainBow/Auto-Git-Annotate/releases) 下载最新的 `.zip` 文件
2. 打开 IDE → `Settings` → `Plugins`
3. 点击 ⚙️ → `Install Plugin from Disk...`
4. 选择下载的 `.zip` 文件
5. 重启 IDE

### 方式二：从源码构建

```bash
# 克隆仓库
git clone https://github.com/WyRainBow/Auto-Git-Annotate.git
cd Auto-Git-Annotate

# 构建插件
./gradlew buildPlugin

# 插件包位置
# build/distributions/auto-git-annotate-1.0.0.zip
```

## 🛠️ 开发环境

- JDK 17+
- Gradle 8.5
- IntelliJ Platform SDK 2023.3+

## 📁 项目结构

```
Auto-Git-Annotate/
├── build.gradle.kts                    # Gradle 构建配置
├── settings.gradle.kts                 # Gradle 设置（含镜像源）
├── src/main/
│   ├── kotlin/com/github/autoAnnotate/
│   │   ├── AutoAnnotateStartupActivity.kt   # 启动时注册监听器
│   │   └── AutoAnnotateFileListener.kt      # 文件打开事件监听
│   └── resources/META-INF/
│       └── plugin.xml                  # 插件配置
└── README.md
```

## 🔧 工作原理

1. 插件在 IDE 启动时注册 `FileEditorManagerListener`
2. 当用户打开任意文件时\监听器捕获 `fileOpened` 事件
3. 通过 `ActionManager` 自动执行内置的 `Annotate` 动作
4. Git Blame 信息自动显示在编辑器左侧

## ❓ 常见问题

**Q: Annotate 没有自动显示？**
- 确保文件所在项目是 Git 仓库
- 确保文件已被 Git 追踪（不是新建的未提交文件）
- 检查 IDE 是否启用了 Git 集成

**Q: 想要关闭自动 Annotate？**
- `Settings` → `Plugins` → 搜索 "Auto Git Annotate" → 禁用插件

**Q: 支持哪些 IDE 版本？**
- 支持 2023.3（build 233）到 2025.3（build 253）的所有 JetBrains IDE

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📄 License

[MIT License](LICENSE)

---

**⭐ 如果这个插件对你有帮助\请给个 Star！**
