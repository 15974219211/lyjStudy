package com.my.study.publish;


/**
 * 进行包装，然后安全的发布
 */
public class SoftPublicUser {
    private final UserVo user;

    public UserVo getUser() {
        return user;
    }

    public SoftPublicUser(UserVo user) {
        this.user = new SynUser(user);
    }

    private static class SynUser extends UserVo{
        private final UserVo userVo;
        private final Object lock = new Object();

        public SynUser(UserVo userVo) {
            this.userVo = userVo;
        }

        @Override
        public int getAge() {
            synchronized (lock){
                return userVo.getAge();
            }
        }

        @Override
        public void setAge(int age) {
            synchronized (lock){
                userVo.setAge(age);
            }
        }
    }

}
