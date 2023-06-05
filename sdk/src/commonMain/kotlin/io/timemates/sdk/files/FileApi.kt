package io.timemates.sdk.files

import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.internal.flatMap
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.providers.getAsResult
import io.timemates.sdk.files.requests.GetFileBytesRequest
import io.timemates.sdk.files.requests.UploadFileRequest
import io.timemates.sdk.files.types.FileType
import io.timemates.sdk.files.types.value.FileId
import io.timemates.sdk.files.types.value.FileName
import kotlinx.coroutines.flow.Flow

/**
 * Provides functionality for interacting with files through the API.
 *
 * @property engine The TimeMatesRequestsEngine instance used for making API requests.
 * @property tokenProvider The access hash provider for authentication purposes.
 */
public class FileApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {
    /**
     * Uploads a file with the specified type and byte data.
     *
     * @param fileName The name of the original file (with extension).
     * @param fileType The type of the file being uploaded.
     * @param bytes The byte data of the file to upload as a Flow of ByteArrays.
     * @return A Result object containing the ID of the uploaded file if successful, or an error otherwise.
     */
    public suspend fun upload(fileName: FileName, fileType: FileType, bytes: Flow<ByteArray>): Result<FileId> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(UploadFileRequest(token, bytes, fileName, fileType)) }
            .map { it.fileId }
    }

    /**
     * Retrieves the byte data of a file with the specified ID.
     *
     * @param fileId The ID of the file to retrieve byte data for.
     * @return A Result object containing a Flow of ByteArrays representing the file's byte data if successful, or an error otherwise.
     */
    public suspend fun getBytes(fileId: FileId): Result<Flow<ByteArray>> {
        return engine.execute(GetFileBytesRequest(fileId)).map(GetFileBytesRequest.Result::bytes)
    }
}
