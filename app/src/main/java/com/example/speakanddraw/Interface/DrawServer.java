package com.example.speakanddraw.Interface;
import com.example.speakanddraw.model.StableDiffResponse;
import com.example.speakanddraw.model.TextToImgRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface DrawServer {
    @POST
    Call<StableDiffResponse> textToImageRequest(@Url String str, @Header("Content-Type") String str2, @Body TextToImgRequest textToImgRequest);

    @POST("v1/generation/stable-diffusion-xl-beta-v2-2-2/text-to-image")
    Call<StableDiffResponse> textToImageRequestModelTwo(@Header("Content-Type") String str, @Body TextToImgRequest textToImgRequest);


    public static final class DefaultImpls {
        public static /* synthetic */ Call textToImageRequest(DrawServer drawServer, String str, String str2, TextToImgRequest textToImgRequest, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str2 = "application/json";
                }
                return drawServer.textToImageRequest(str, str2, textToImgRequest);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: textToImageRequest");
        }

        public static /* synthetic */ Call textToImageRequestModelTwo$default(DrawServer drawServer, String str, TextToImgRequest textToImgRequest, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = "application/json";
                }
                return drawServer.textToImageRequestModelTwo(str, textToImgRequest);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: textToImageRequestModelTwo");
        }
    }
}
