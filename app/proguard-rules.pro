# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.google.android.material.** { *; }
-keep class com.google.android.gms.**
-keep class com.google.android.gms.location.**

-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**

-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

-keep class org.apache.** { *; }
-keep interface org.apache.** { *; }
-dontwarn org.apache.**

-keep class org.w3c.** { *; }
-keep interface org.w3c.** { *; }
-dontwarn org.w3c.**

-keep class javax.xml.** { *; }
-keep interface javax.xml.** { *; }
-dontwarn javax.xml.**
-dontwarn javax.annotation.**

-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn retrofit2.**
