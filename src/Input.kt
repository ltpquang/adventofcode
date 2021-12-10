import java.io.File
import java.io.FileReader
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class Input {
    companion object {
        fun getFor(year: Int, day: Int): String {
            val localFile = File(String.format("./src/days/day%02d/input.txt", day))
            if (localFile.exists()) {
                return localFile.readText()
            }

            val client = HttpClient.newBuilder().build();
            val cookieReader = FileReader("./adventcookie")
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://adventofcode.com/$year/day/$day/input"))
                .header("cookie", cookieReader.readText())
                .build();

            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            val result = response.body()

            if (localFile.createNewFile()) {
                localFile.writeText(result)
            }

            return result
        }
    }
}