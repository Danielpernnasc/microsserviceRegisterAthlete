package com.trainday.bodybuilder.infra.MyTrain;

import com.trainday.bodybuilder.infra.Config.FeignConfig;
import com.trainday.bodybuilder.infra.DTO.response.MyTrainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "train-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface MyTrainbyPeP {

    @GetMapping("/train/athlete/MyTrain/{cpf}")
    MyTrainResponse findByMyTrain(@PathVariable String cpf);
}
