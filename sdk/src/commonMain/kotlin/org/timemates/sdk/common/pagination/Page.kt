package org.timemates.sdk.common.pagination

import org.timemates.sdk.common.types.TimeMatesEntity

public data class Page<T : TimeMatesEntity>(
    val results: List<T>,
    val nextPageToken: PageToken?,
) : TimeMatesEntity()