val ktor_version: String by project
plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
}

group = "ktor.server.base"
version = "1.0.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.mock.api.MainKt")
}

dependencies {
    implementation("io.ktor:ktor-network-tls-certificates:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("io.ktor:ktor-server-double-receive:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-data-conversion:$ktor_version")
    implementation("io.ktor:ktor-server-compression:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("org.apache.commons:commons-email:1.5")
    implementation("io.insert-koin:koin-ktor:3.5.5")
    implementation("ch.qos.logback:logback-classic:1.5.3")
    implementation("org.litote.kmongo:kmongo-coroutine:4.11.0")
    implementation("io.lettuce:lettuce-core:6.3.2.RELEASE")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Main-Class" to "com.mock.api.MainKt"
        )
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}