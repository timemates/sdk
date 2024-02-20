![Platform](https://img.shields.io/badge/platform-jvm-yellow)
![Latest GitHub release](https://img.shields.io/github/v/release/timemates/sdk?include_prereleases)
![GitHub](https://img.shields.io/github/license/timemates/sdk)
![GitHub issues](https://img.shields.io/github/issues/timemates/sdk)
# TimeMates SDK
The TimeMates SDK is a software development kit that provides support for the TimeMates API,
which is built on top of gRPC. It offers a convenient and efficient way to interact with the TimeMates 
platform and integrate its features into your own applications.

## Usages example
To demonstrate the usage of the SDK, consider the following example:

```kotlin
val engine = RSocketTimeMatesRequestsEngine(
    coroutineScope = Dispatchers.IO,
)
val emailAuth = AccountLoginApi(engine).email
val email = EmailAddress.create("developer@y9vad9.com").getOrElse { return }

val authorizationResult = emailAuth.authorize(email)
    // requestCode returns ConfirmationCode from user
    .map { verificationHash -> verificationHash to requestCode() }
    .map { (verificationHash, code) -> emailAuth.confirm(verificationHash, verificationHash) }
    // if it's new account, there's no authorization present
    // until we complete our registration
    .map { (isNewAccount, authorization) -> ... }
```
In the provided code snippet, the SDK utilizes the Kotlin Result API extensively.

It's important to note that the SDK's value objects, such as `EmailAddress`
and `ConfirmationCode`, also employ the Result API.
This ensures that the validation process is properly handled, and the client can take appropriate actions based on
the success or failure of the operations.

### Pagination
In some cases, we need to get a large set of data (for example, list of timers or members). We can use extensions provided for Api classes:
```kotlin
val timersApi: TimersApi = TimersApi(createEngine(), getTokenProvider())
val iterator = timersApi.getUserTimersPages() // lazy iterator, it queries server only on `next()`

// using collections (loads everything at once)
iterator.toList().forEach { timer -> println(timer.name.string) }

// using flow
iterator.asFlow()
    .collectLatest { page -> println(page.joinToString("\n")) }

// using sequences (actually, it's just toList() + asSequence() from stdlib)
iterator.asSequence()
    .map { timer -> timer.name.string }
    .forEach { name -> println(name) }

// iterate directly
iterator.forEachPage { page -> /* ... */ }
iterator.forEach { timer -> /* ... */ }
```
> **Warning** <br>
> `asSequence` is experimental API and needs more considerations about possible misunderstanding of non-laziness way of loading and iterating data at start. You can just use `iterator.toList().asSequence()` or simply opt-in `ExperimentalApi` annotation.

Overall, you can take a look at sources of [PagesIterator](/sdk/src/commonMain/kotlin/io/timemates/sdk/common/pagination/PagesIterator.kt) to get more information.

## Implementation
> **Warning** <br>
> This is a very-very alpha version of the library, and it can contain unexpected bugs among with
> changing of public API.

You can implement sdk in next way:
```kotlin
repositories {
    maven("https://maven.timemates.io")
}

dependencies {
    implementation("org.timemates:sdk:$version")
    implementation("org.timemates:rsocket-engine:$version")
}
```

## Community
We have a vibrant and supportive community for TimeMates SDK users where 
you can ask questions, share ideas, and connect with fellow developers. Join our Telegram group
[@timemates](https://t.me/timemates) to engage with the community.
