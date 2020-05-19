package com.durid.workoutjournal.ui.forms

import android.content.DialogInterface
import android.view.View
import com.durid.workoutjournal.model.DialogType

interface BluePrintFormInterface<T> {
    var dialogType: DialogType
    fun InitFields(formView : View, dialogType: DialogType)
    fun SetSave(item : T?, dialogType: DialogType)
    fun SetCancel(dialog : DialogInterface)
}