package com.my.study.transefer;



import java.util.Random;
/**
 *安全转账1
 */
public class SafeOperateTwo implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {
        Random r = new Random();
        while(true){
            if(from.getLock().tryLock()){
                System.out.println(Thread.currentThread().getName()
                        +" get"+from.getName());
                try{
                    if(to.getLock().tryLock()){
                        try{
                            System.out.println(Thread.currentThread().getName()
                                    +" get"+to.getName());
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(from);
                            System.out.println(to);
                            break;
                        }finally{
                            to.getLock().unlock();
                        }
                    }
                }finally {
                    from.getLock().unlock();
                }

            }
            //Thread.sleep(r.nextInt(2));
        }

    }
}
