package org.nhhackaton.deposit.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhhackaton.api.easypament.TransferApiService;
import org.nhhackaton.api.easypament.dto.DrawingTransferRequest;
import org.nhhackaton.api.easypament.dto.DrawingTransferResponse;
import org.nhhackaton.api.easypament.dto.ReceivedTransferRequest;
import org.nhhackaton.api.easypament.dto.ReceivedTransferResponse;
import org.nhhackaton.api.finaccount.FinAccountApiService;
import org.nhhackaton.api.finaccount.dto.*;
import org.nhhackaton.api.p2p.P2PApiService;
import org.nhhackaton.api.p2p.dto.VirtualAccountRequest;
import org.nhhackaton.api.p2p.dto.VirtualAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/deposit")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DepositController {

    private final FinAccountApiService finAccountApiService;
    private final TransferApiService transferApiService;
    private final P2PApiService p2PApiService;

    @GetMapping("/")
    public ResponseEntity getDeposit(@RequestParam("identity") String identity) {

        return null;
    }

    @PostMapping("/apply-invest")
    public ResponseEntity applyInvest(@RequestBody ApplyInvestRequest applyInvestRequest) {
        OpenFinAccountRequest openFinAccountRequest = OpenFinAccountRequest.builder()
                .DrtrRgyn(applyInvestRequest.getDrtrRgyn())
                .BrdtBrno(applyInvestRequest.getBrdtBrno())
                .Bncd(applyInvestRequest.getBncd())
                .Acno(applyInvestRequest.getAcno()).build();
        ResponseEntity<OpenFinAccountResponse> open = finAccountApiService.open(openFinAccountRequest);
        System.out.println(open.getBody().getHeader().getRsms());

        CheckFinAccountRequest checkFinAccountRequest = CheckFinAccountRequest.builder()
                .Rgno(open.getBody().getRgno())
                .BrdtBrno(applyInvestRequest.getBrdtBrno()).build();

        ResponseEntity<CheckFinAccountResponse> check = finAccountApiService.check(checkFinAccountRequest);
        System.out.println(check.getBody().getHeader().getRsms());

        //출금이체
        DrawingTransferRequest drawingTransferRequest = DrawingTransferRequest.builder()
                .MractOtlt("입금되었습니다")
                .DractOtlt("출금되었습니다")
                .Tram(applyInvestRequest.getInvestPrice())
                .FinAcno("00820100006710000000000004533").build();
        ResponseEntity<DrawingTransferResponse> drawingTransferResponse = transferApiService.draw(drawingTransferRequest);
        System.out.println(drawingTransferResponse.getBody().getHeader().getRsms());

        //가상계좌발급
        VirtualAccountRequest virtualAccountRequest = VirtualAccountRequest.builder()
                .P2PVractUsg("1")
                .P2PCmtmNo("0000000000")
                .ChidSqno("0000000000")
                .InvstBrwNm(applyInvestRequest.getName()).build();
        ResponseEntity<VirtualAccountResponse> virtualAccountResponse = p2PApiService.create(virtualAccountRequest);
        System.out.println(virtualAccountResponse.getBody().getHeader().getRsms());

        ReceivedTransferRequest receivedTransferRequest = ReceivedTransferRequest.builder()
                .MractOtlt("입금되었습니다")
                .DractOtlt("출금되었습니다")
                .Tram(applyInvestRequest.getInvestPrice())
                .Acno("3020000002859")
                .Bncd("011").build();

        ResponseEntity<ReceivedTransferResponse> receivedTransferResponse = transferApiService.receive(receivedTransferRequest);
        System.out.println(receivedTransferResponse.getBody().getHeader().getRsms());

        return ResponseEntity.ok().build();
    }
}
