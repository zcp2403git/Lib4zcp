-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontoptimize
-verbose
-dontwarn
-dontskipnonpubliclibraryclassmembers
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keep public class * extends android.support.v4.**
 
-keepclasseswithmembernames class * {# 保持 native 方法不被混淆
    native <methods>;
}
 
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
 
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
 
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
 
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
 
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable {
	static final long serialVersionUID;
	private static final java.io.ObjectStreamField[] serialPersistentFields;
	private void writeObject(java.io.ObjectOutputStream);
	private void readObject(java.io.ObjectInputStream);
	java.lang.Object writeReplace();
	java.lang.Object readResolve();
}

-keepattributes *Annotation*
-renamesourcefileattribute SourceFile    
-keepattributes SourceFile,LineNumberTable

-dontwarn org.apache.commons.logging.*
-keep class org.apache.commons.logging.** {*;}
-keep class org.apache.commons.logging.impl.** {*;}

-keep class * extends andriod.app.Dialog

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson

-keep class android.support.v7.** {*;}


#-keep class de.greenrobot.event.**{*;}
#-keepclassmembers class ** {
#   public void onEvent*(**);
#}

-ignorewarning