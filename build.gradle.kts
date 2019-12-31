import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	java
	kotlin("jvm") version "1.3.50"
}

buildscript {
    var kotlinVersion = "1.3.50"
	repositories {
		mavenCentral()
	}
	dependencies {
	}
}

apply {
	plugin("java")
	plugin("idea")
}


repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    implementation("top.ourfor:lib:+")
	compile("org.jsoup:jsoup:1.12.1")
}


java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}


