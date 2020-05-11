package ru.mobile.lukslol.util.type

import androidx.lifecycle.LiveData

class NonNullLiveData<T>(value: T) : LiveData<T>(value) {

    override fun getValue() = super.getValue()!!

    public override fun setValue(value: T) {
        super.setValue(value)
    }

    public override fun postValue(value: T) {
        super.postValue(value)
    }
}