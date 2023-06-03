package io.timemates.api.grpc.interceptors

import io.grpc.*
import io.timemates.api.authorizations.options.OmitAuthorizationOption
import io.timemates.sdk.common.providers.AccessHashProvider

public class AuthorizationInterceptor(
    private val tokenProvider: AccessHashProvider,
) : ClientInterceptor {
    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        val optionValue = callOptions.getOption(OmitAuthorizationOption.omitAuthorization)
        val metadata = Metadata()
        metadata.put(Metadata.Key.of("custom-header", Metadata.ASCII_STRING_MARSHALLER), optionValue)

        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions.withCallCredentials(null))) {
            override fun start(responseListener: Listener<RespT>?, headers: Metadata?) {
                metadata.let { headers?.merge(it) }
                super.start(responseListener, headers)
            }
        }
    }
}