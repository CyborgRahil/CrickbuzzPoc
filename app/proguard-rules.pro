-target 1.7
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

-keep class poc.com.cricbuzzpoc** { *; }
-keep class android.support.test.espresso.IdlingResource { *; }
-keep class android.support.test.espresso.IdlingRegistry { *; }
-keep class com.google.common.base.Preconditions { *; }
-keep class android.arch.** { *; }

# Proguard rules that are applied to your test apk/code.
-ignorewarnings

-keepattributes *Annotation*

-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter
# Uncomment this if you use Mockito
-dontwarn org.mockito.**

-printmapping mapping.txt

#-dump bin/class_files.txt
#-printseeds bin/seeds.txt
#-printusage bin/unused.txt
#-printmapping bin/mapping.txt
-keepattributes SourceFile,LineNumberTable,Signature
-renamesourcefileattribute SourceFile

-keep class com.google.inject.Binder
-keepclassmembers class * {
    @com.google.inject.Inject <init>(...);
}
# There's no way to keep all @Observes methods, so use the On*Event convention to identify event handlers
-keepclassmembers class * {
    void *(**On*Event);
}
# Guava

-dontwarn sun.misc.Unsafe
-dontwarn com.google.common.**
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn android.support.**

-dontnote **ILicensingService
-dontnote com.google.inject.Inject
-dontnote Object

# Suppress the notes and warns of the libs used by kmk video libs
-dontnote com.google.**
-dontwarn com.google.**
-dontnote rx.**
-dontwarn rx.**
-dontnote java.**
-dontwarn java.**
-dontnote kotlin.jvm.internal.**

# Gson
-keepattributes *Annotation*
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


# RxJava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

#grpc
-dontwarn javax.naming.**
-dontwarn com.google.common.**
-dontwarn com.google.errorprone.annotations.**
-keep class io.grpc.** {*;}
-keep class sun.reflect.** {*;}
-keep class org.conscrypt.** {*;}

# Room
-keep class android.arch.persistence.** {*;}
-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource

# Gson
-keep class com.google.gson.** {*;}

-keep class **.R$* {
   <fields>;
}

