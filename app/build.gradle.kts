plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}

tasks.register<JavaExec>("runGenerateReceipt") {
    group = "application"
    mainClass.set("com.example.myapplication.minipeta3.Cadelina_GenerateReceipt")
    val variant = "debug"
    classpath = project.files(
        tasks.named("compile${variant.replaceFirstChar { it.uppercase() }}UnitTestJavaWithJavac"),
        configurations.getByName("${variant}UnitTestRuntimeClasspath")
    )
    standardInput = System.`in`
}

