package com.comparator.spring.controller;

import com.comparator.controller.DataController;
import com.comparator.controller.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diff")
public class SpringController {

    private final DataController controller;

    @Autowired
    public SpringController(final DataController controller) {
        this.controller = controller;
    }

    @RequestMapping(value = "/{id}/left", method = RequestMethod.POST)
    public ResponseEntity<Void> saveLeft(@PathVariable("id") final String id, @RequestBody final String data) {
        controller.saveDataLeft(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}/right", method = RequestMethod.POST)
    public ResponseEntity<Void> saveRight(@PathVariable("id") final String id, @RequestBody final String data) {
        controller.saveDataRight(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseData> getUser(@PathVariable("id") final String id) {
        return ResponseEntity.ok(controller.compareData(id));
    }
}