package org.example;


public class Main{
    public static void main(String[] args) {
        /*List<UserEntity> users = DaoFactory.INSTANCE.getUserDao().getAll();
        users.forEach(user -> System.out.println(user.toString()));

        Optional<UserEntity> userEntity = DaoFactory.INSTANCE.getUserDao().get(252);
        System.out.println("----------- " + userEntity.get().getExtraUserDataEntity().getPassportNumber());
        */

        /*ExtraUserDataEntity extraUserData = ExtraUserDataEntity.builder()
                .name("sasha")
                .lastname("otraschenok")
                .phone("+375297456")
                .passportNumber("HB12345678")
                .build();

        Optional<RoleEntity> roleEntity = DaoFactory.INSTANCE.getRoleDao().get(1);

        UserEntity userEntity = UserEntity.builder()
                .extraUserDataEntity(extraUserData)
                .login("nekolover")
                .roleEntity(roleEntity.get())
                .password("1234")
                .build();

        DaoFactory.INSTANCE.getUserDao().save(userEntity);*/

        /*ApplicationContext ctx = new AnnotationConfigApplicationContext(DaoFactory.class);
        DaoFactory daoFactory = ctx.getBean(DaoFactory.class);
*/
        //daoFactory.getUserDao().getAll().forEach(user -> System.out.println(user.toString()));
        //daoFactory.getRoleDao().getAll().forEach(user -> System.out.println(user.toString()));


        //SpringApplication.run(Main.class, args);
    }

    public void run(String... args) throws Exception {

    }
}