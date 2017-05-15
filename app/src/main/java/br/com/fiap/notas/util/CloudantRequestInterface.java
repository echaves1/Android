package br.com.fiap.notas.util;

import br.com.fiap.notas.entity.CloudantResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by logonpf on 24/04/2017.
 */

public interface CloudantRequestInterface {
    @GET("_all_docs?include_docs=true")
    Call<CloudantResponse> getAllJson();
}
