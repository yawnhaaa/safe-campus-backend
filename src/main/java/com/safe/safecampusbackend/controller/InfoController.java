package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.service.InfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InfoController {
    private final InfoService infoService;
}
