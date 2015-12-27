# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.netease.commonlibrary.Annotations.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.netease.commonlibrary.Annotations.DoNotStrip class *
-keepclassmembers class * {
    @com.netease.commonlibrary.Annotations.DoNotStrip *;
}

