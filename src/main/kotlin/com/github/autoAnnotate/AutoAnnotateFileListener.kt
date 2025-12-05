package com.github.autoAnnotate

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

/**
 * 监听文件打开事件，自动执行 Git Annotate
 */
class AutoAnnotateFileListener(private val project: Project) : FileEditorManagerListener {

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        // 延迟执行，确保编辑器已完全加载
        ApplicationManager.getApplication().invokeLater {
            triggerAnnotate(source, file)
        }
    }

    private fun triggerAnnotate(source: FileEditorManager, file: VirtualFile) {
        try {
            // 获取当前文件的编辑器
            val editors = source.getEditors(file)
            
            for (fileEditor in editors) {
                if (fileEditor is TextEditor) {
                    val editor: Editor = fileEditor.editor
                    
                    // 使用 ActionManager 执行 Annotate 动作
                    // 动作 ID: Annotate (这是 Git Annotate 的动作 ID)
                    val actionManager = ActionManager.getInstance()
                    val annotateAction = actionManager.getAction("Annotate")
                    
                    if (annotateAction != null) {
                        // 创建数据上下文
                        val dataContext = SimpleDataContext.builder()
                            .add(CommonDataKeys.PROJECT, project)
                            .add(CommonDataKeys.EDITOR, editor)
                            .add(CommonDataKeys.VIRTUAL_FILE, file)
                            .build()
                        
                        // 创建事件并执行动作
                        val event = AnActionEvent.createFromAnAction(
                            annotateAction,
                            null,
                            "AutoAnnotatePlugin",
                            dataContext
                        )
                        
                        ApplicationManager.getApplication().invokeLater {
                            try {
                                annotateAction.actionPerformed(event)
                            } catch (e: Exception) {
                                // 静默处理异常
                            }
                        }
                    }
                    
                    break // 只处理第一个文本编辑器
                }
            }
        } catch (e: Exception) {
            // 静默处理，避免影响用户体验
        }
    }
}
