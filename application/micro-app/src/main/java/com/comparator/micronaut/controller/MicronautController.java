package com.comparator.micronaut.controller;

import com.comparator.controller.DataController;
import com.comparator.controller.model.ResponseData;
import com.comparator.micronaut.config.Config;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

@Controller("/v1/diff")
public class MicronautController {

    private final DataController controller = Config.getDataController();

    @Post(value ="/{id}/left", consumes = MediaType.TEXT_PLAIN, produces = MediaType.TEXT_PLAIN)
    public HttpStatus saveLeft(@PathVariable("id") final String id, @Body final String data) {
        controller.saveDataLeft(id, data);
        return HttpStatus.CREATED;
    }

    @Post(value ="/{id}/right", consumes = MediaType.TEXT_PLAIN, produces = MediaType.TEXT_PLAIN)
    public HttpStatus saveRight(@PathVariable("id") final String id, @Body final String data) {
        controller.saveDataRight(id, data);
        return HttpStatus.CREATED;
    }

    @Get(value = "/{id}")
    public ResponseData getUser(@PathVariable("id") final String id) {
        return controller.compareData(id);
    }

}