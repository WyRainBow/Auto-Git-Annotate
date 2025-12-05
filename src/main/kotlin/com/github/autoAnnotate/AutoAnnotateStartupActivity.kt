package com.github.autoAnnotate

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener

/**
 * 启动时注册文件打开监听器
 */
class AutoAnnotateStartupActivity : ProjectActivity {
    
    override suspend fun execute(project: Project) {
        // 订阅文件打开事件
        project.messageBus.connect().subscribe(
            FileEditorManagerListener.FILE_EDITOR_MANAGER,
            AutoAnnotateFileListener(project)
        )
    }
}

