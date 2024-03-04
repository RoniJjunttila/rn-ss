package com.screenshotapp

import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.soloader.SoLoader
import java.lang.reflect.InvocationTargetException
import kotlin.collections.listOf

class MainApplication : Application(), ReactApplication {

  private val _reactNativeHost = object : ReactNativeHost(this) {
      override fun getUseDeveloperSupport(): Boolean = BuildConfig.DEBUG

      override fun getPackages(): List<ReactPackage> {
          return PackageList(this).packages.apply {
              // Add your custom packages here
              add(CustomReactPackage())
          }
      }

      override fun getJSMainModuleName(): String = "index"
  }

  // This is the correct way to override reactNativeHost as a public getter in Kotlin
  override val reactNativeHost: ReactNativeHost
      get() = _reactNativeHost

  override fun onCreate() {
      super.onCreate()
      SoLoader.init(this, false)
  }
}
