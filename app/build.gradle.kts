plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.touch.player"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.touch.player"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    implementation(files("../plugin/toucheplugin.aar"))

    implementation("com.google.android.material:material:1.11.0")

    // Rest API
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    // Coroutines"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.fragment:fragment-ktx:1.4.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-common:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    implementation("androidx.preference:preference-ktx:1.2.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // amazon aws
    implementation("com.amazonaws:aws-android-sdk-s3:2.17.1")

    // Dot Indicator
    implementation("ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:1.0.0")
    implementation("com.tbuonomo:dotsindicator:5.0")

    // Simple RatingBar
//    implementation("com.github.ome450901:SimpleRatingBar:1.5.1")

    // Exact RatingBar
//    implementation("com.github.Jamshid-M:ExactRatingBar:1.0.0")

    // Vertical Seekbar
    implementation("com.h6ah4i.android.widget.verticalseekbar:verticalseekbar:1.0.0")

    //exoplayer
    implementation("com.google.android.exoplayer:exoplayer-core:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-dash:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-hls:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-rtsp:2.17.1")
}