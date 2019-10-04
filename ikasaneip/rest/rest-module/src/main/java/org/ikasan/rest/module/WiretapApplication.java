/*
 * $Id$  
 * $URL$
 * 
 * ====================================================================
 * Ikasan Enterprise Integration Platform
 * 
 * Distributed under the Modified BSD License.
 * Copyright notice: The copyright for this software and a full listing 
 * of individual contributors are as shown in the packaged copyright.txt 
 * file. 
 * 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 *
 *  - Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 *
 *  - Neither the name of the ORGANIZATION nor the names of its contributors may
 *    be used to endorse or promote products derived from this software without 
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 */
package org.ikasan.rest.module;

import org.ikasan.rest.module.dto.ErrorDto;
import org.ikasan.rest.module.util.DateTimeConverter;
import org.ikasan.spec.flow.FlowEvent;
import org.ikasan.spec.module.ModuleService;
import org.ikasan.spec.search.PagedSearchResult;
import org.ikasan.spec.wiretap.WiretapEvent;
import org.ikasan.spec.wiretap.WiretapService;
import org.ikasan.trigger.model.Trigger;
import org.ikasan.wiretap.listener.JobAwareFlowEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Module application implementing the REST contract
 */
@RequestMapping("/rest/wiretap")
@RestController
public class WiretapApplication {

    @Autowired
    private JobAwareFlowEventListener jobAwareFlowEventListener;

    @Autowired
    /** The wiretap listener */
    private WiretapService<FlowEvent,PagedSearchResult<WiretapEvent>> wiretapService;

    @Autowired
    /** The module container (effectively holds the DTO) */
    private ModuleService moduleService;

    private DateTimeConverter dateTimeConverter = new DateTimeConverter();

    @RequestMapping(method = RequestMethod.PUT,
            value = "/createTrigger/{moduleName}/{flowName}/{flowElementName}/{relationship}/{jobType}")
    @PreAuthorize("hasAnyAuthority('ALL','WebServiceAdmin')")

    public ResponseEntity createTrigger(@PathVariable("moduleName") String moduleName,
                                        @PathVariable("flowName") String flowName,
                                        @PathVariable("flowElementName") String flowElementName,
                                        @PathVariable("relationship") String relationship,
                                        @PathVariable("jobType") String jobType, @RequestBody String timeToLive) {
        HashMap<String, String> params = new HashMap<String, String>();

        if (timeToLive != null && timeToLive.length() > 0) {
            params.put("timeToLive", timeToLive);
        }

        Trigger trigger = new Trigger(moduleName, flowName, relationship, jobType, flowElementName, params);

        try {
            this.jobAwareFlowEventListener.addDynamicTrigger(trigger);
        } catch (Exception e) {
            return new ResponseEntity("An error has occurred trying to create a new trigger: " + e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity("Trigger successfully created!", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT,
            value = "/deleteTrigger")
    @PreAuthorize("hasAnyAuthority('ALL','WebServiceAdmin')")
    public ResponseEntity deleteTrigger(@RequestBody Long triggerId) {
        try {
            this.jobAwareFlowEventListener.deleteDynamicTrigger(triggerId);
        } catch (Exception e) {

            return new ResponseEntity("An error has occurred trying to delete a trigger: " + e.getMessage(), HttpStatus.FORBIDDEN);

        }

        return new ResponseEntity("Trigger successfully deleted!", HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET,
        value = "/")
    @PreAuthorize("hasAnyAuthority('ALL','WebServiceAdmin')")
    public ResponseEntity get(
        @RequestParam(value = "pageNumber",defaultValue = "0") int pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "20") int pageSize,
        @RequestParam(value = "orderBy",defaultValue = "identifier") String orderBy,
        @RequestParam(value = "orderAscending",defaultValue = "true") boolean orderAscending,
        @RequestParam(value = "flow",required = false) String flow,
        @RequestParam(value = "componentName",required = false) String componentName,
        @RequestParam(value = "payloadId",required = false) String payloadId,
        @RequestParam(value = "eventId",required = false) String eventId,
        @RequestParam(value = "payloadContent",required = false) String payloadContent,
        @RequestParam(value= "fromDateTime",required = false) String fromDateTime,
        @RequestParam(value= "untilDateTime",required = false) String untilDateTime
        ) {

        String moduleName = moduleService.getModules().get(0).getName();
        PagedSearchResult<WiretapEvent> pagedResult = null;
        try
        {
            pagedResult = wiretapService.findWiretapEvents(
                pageNumber, pageSize, orderBy, orderAscending, new HashSet(){{add(moduleName);}},
                flow, componentName, eventId, payloadId,
                dateTimeConverter.getDate(fromDateTime), dateTimeConverter.getDate(untilDateTime), payloadContent);
            return new ResponseEntity(pagedResult, HttpStatus.OK);

        }
        catch (ParseException e)
        {
            return new ResponseEntity(new ErrorDto("fromDateTime or untilDateTime has invalid dateTime format not following yyyy-MM-dd'T'HH:mm:ss."),
                HttpStatus.BAD_REQUEST);
        }

    }

}
