package com.example.demo.dto.response;

public class BankInfoResponse {
    private long bankCount;
    private int bankProgress;

    public BankInfoResponse(long bankCount, int bankProgress) {
        this.bankCount = bankCount;
        this.bankProgress = bankProgress;
    }

    public BankInfoResponse() {
    }

    public long getBankCount() {
        return bankCount;
    }

    public void setBankCount(long bankCount) {
        this.bankCount = bankCount;
    }

    public int getBankProgress() {
        return bankProgress;
    }

    public void setBankProgress(int bankProgress) {
        this.bankProgress = bankProgress;
    }
}
