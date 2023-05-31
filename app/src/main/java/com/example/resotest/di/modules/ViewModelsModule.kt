package com.example.resotest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.resotest.view.ViewModelFactory
import com.example.resotest.view.agencies.AgenciesViewModel
import com.example.resotest.view.subject.SubjectViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AgenciesViewModel::class)
    protected abstract fun agenciesViewModel(agenciesViewModel: AgenciesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubjectViewModel::class)
    protected abstract fun subjectViewModel(subjectViewModel: SubjectViewModel): ViewModel

}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)