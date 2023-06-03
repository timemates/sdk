![Platform](https://img.shields.io/badge/platform-jvm-yellow)
![Latest GitHub release](https://img.shields.io/github/v/release/timemates/sdk?include_prereleases)
![GitHub](https://img.shields.io/github/license/timemates/sdk)
![GitHub issues](https://img.shields.io/github/issues/timemates/sdk)
# TimeMates SDK
The TimeMates SDK is a software development kit that provides support for the TimeMates API, which is built on top of gRPC. It offers a convenient and efficient way to interact with the TimeMates platform and integrate its features into your own applications.

## Usages example
To demonstrate the usage of the SDK, consider the following example:
```kotlin
val emailAuth = AccountLoginApi(GrpcTimeMatesRequestsEngine()).email
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

## Implementation
> **Note** <br>
> We're only at development stage, so there's no way to implement it until
> we finish our development on SDK. <br><br>

## Community
We have a vibrant and supportive community for TimeMates SDK users where 
you can ask questions, share ideas, and connect with fellow developers. Join our Telegram group
[@timemates](https://t.me/timemates) to engage with the community.
