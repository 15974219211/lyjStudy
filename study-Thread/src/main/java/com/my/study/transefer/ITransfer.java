package com.my.study.transefer;



/**
 *
 *转账
 */
public interface ITransfer {
    void transfer(UserAccount from, UserAccount to, int amount)
    		throws InterruptedException;
}
