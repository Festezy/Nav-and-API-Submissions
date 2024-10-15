##---------------Begin: proguard configuration for SQLCipher  ----------
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}


# Keep Gson models (in case you're using custom TypeAdapters or Gson serialization)
-keep class com.google.gson.** { *; }

# Prevent obfuscation of Kotlin data classes (used in Gson serialization)
-keep class **Kt { *; }

# Keep Kotlin Metadata (important for reflection in Kotlin)
-keepattributes KotlinMetadata

# Prevent warnings for Gson and OkHttp (adjust as needed)
-dontwarn com.google.gson.*

##---------------Begin: proguard configuration for Retrofit ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
@retrofit2.http.* <methods>;
}

# Keep OkHttp3 classes and CertificatePinner
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keepclassmembers class okhttp3.** {
    *;
}
-keep class com.squareup.okhttp3.logging.** { *; }

# Keep certificate-related classes in case they are being removed
-keep class okhttp3.internal.tls.** { *; }

# Keep necessary code that CertificatePinner may reference via reflection
-keepnames class okhttp3.CertificatePinner { *; }

# Avoid obfuscating okhttp3's internal methods (helps during debugging)
-dontwarn okhttp3.**

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

-keep class com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.** { *; }

-dontwarn kotlinx.**

# Keep ArchTaskExecutor methods
-keep class androidx.arch.core.executor.ArchTaskExecutor { *; }

# Keep Lifecycle-related classes
-keep class androidx.lifecycle.** { *; }

# Don't warn about AndroidX classes
-dontwarn androidx.**


#missing
#-keep class com.example.aplikasi_dicoding_event_navigationdanapi.core.** { *; }
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource$Error
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource$Loading
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource$Success
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.LocalDataSource
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.RemoteDataSource
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiService
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.di.DatabaseModule
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.di.DatabaseModule_ProvideDatabaseFactory
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.di.DatabaseModule_ProvideTourismDaoFactory
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.di.NetworkModule
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.di.NetworkModule_ProvideApiServiceFactory
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.di.NetworkModule_ProvideOkHttpClientFactory
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.repository.IEventsRepository
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsInteractor
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.ui.EventAdapter$OnItemClickCallback
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.ui.EventAdapter
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors
-dontwarn com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.UtilsKt