package com.template.webserver;

import com.template.flows.CreateAndIssueDesignStamp;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

/**
 * Define your API endpoints here.
 */
@RestController
@RequestMapping("/") // The paths for HTTP requests are relative to this base path.
public class Controller {
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(NodeRPCConnection rpc) {
        this.proxy = rpc.proxy;
    }

    @GetMapping(value = "/templateendpoint", produces = "text/plain")
    private String templateendpoint() {
        return "Define an endpoint here.";
    }
    @PostMapping( "create-iou")
    public ResponseEntity<String> issueIOU(HttpServletRequest request) {

//        int amount = Integer. valueOf(request.getParameter("iouValue"));
//        String party = request.getParameter("partyName");
//        // Get party objects for myself and the counterparty.
//
//        CordaX500Name partyX500Name = CordaX500Name.parse(party);
//        Party otherParty = proxy.wellKnownPartyFromX500Name(partyX500Name);

        // Create a new IOU state using the parameters given.
        try {
            // Start the IOUIssueFlow. We block and waits for the flow to return.
            SignedTransaction result = proxy.startTrackedFlowDynamic(CreateAndIssueDesignStamp.CreateAndIssueDesignStampInitiator.class, "Fuji4072","PartyB").getReturnValue().get();
            // Return the response.
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Transaction id "+ result.getId() +" committed to ledger.\n " + result.getTx().getOutput(0));
            // For the purposes of this demo app, we do not differentiate by exception type.
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}