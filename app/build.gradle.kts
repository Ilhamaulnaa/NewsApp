plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.ilham.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ilham.newsapp"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":data"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Splash Api
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Compose Navigation
//    def nav_version = "2.6.0"
    implementation("androidx.navigation:navigation-compose:2.6.0")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.45")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Paging 3
//    def paging_version = "3.1.1"
    implementation("androidx.paging:paging-runtime:3.1.1")
    implementation("androidx.paging:paging-compose:3.2.0-rc01")


//    //Retrofit
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.4.3")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta")

//    //Paging 3
////    def paging_version = "3.1.1"
//    implementation("androidx.paging:paging-runtime:3.1.1")
//    implementation("androidx.paging:paging-compose:3.2.0-rc01")

    //Room
//    def room_version = "2.5.2"
//    implementation("androidx.room:room-runtime:2.5.2")
//    kapt("androidx.room:room-compiler:2.5.2")
//    implementation("androidx.room:room-ktx:2.5.2")
}