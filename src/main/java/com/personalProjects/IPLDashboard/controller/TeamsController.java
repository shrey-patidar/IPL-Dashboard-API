package com.personalProjects.IPLDashboard.controller;

import com.personalProjects.IPLDashboard.dto.ResponseObject;
import com.personalProjects.IPLDashboard.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teams")
@CrossOrigin("*")
public class TeamsController {

    private TeamsService teamsService;

    @Autowired
    public void setTeamsService(TeamsService teamsService){
        this.teamsService = teamsService;
    }

    @GetMapping
    public ResponseObject getAllTeams(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setData(teamsService.getAllTeams());
        return responseObject;
    }

    @GetMapping(value = "/{teamName}")
    public ResponseObject getMatchHistoryByTeam(@PathVariable String teamName,
                                                @RequestParam(required = false) Integer season,
                                                @RequestParam(required = false) Integer pageNo,
                                                @RequestParam(required = false) Integer pageSize){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setData(teamsService.getMatchHistoryByTeam(teamName,season, pageNo, pageSize));
        return responseObject;
    }
}
