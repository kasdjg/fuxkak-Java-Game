public class Hunter {
    String name;
    int life;
    String weapon;
    boolean isLive;

    int attack;
    int defend;

    public Hunter(String name, String weapon) {
        this.name = name;
        this.weapon = weapon;
        life = 100;
        isLive = true;
        attack = 25;
        defend = 8;
    }

    public void fight(Monster monster) {
        if (!isLive) {
            return;
        }
        if (!monster.isLive) {
            return;
        }
        System.out.println(name + "挥舞着" + weapon + "杀向了" + monster.type);
        monster.injured(this);//this表示hunter自己这个对象
        //monster.injured()是指调用monster的injured()方法
        // (this.attack)是指将hunter的attack属性传到monster的injured()方法
        //v4.0改为(this)是指将hunter的所有属性传到monster的injured()方法
    }

    public void injured(Monster monster) {//v4.1改为Monster monster 意为传递monster对象的所有属性
        // 定义丢失的基本生命值
        int lostBasicLife = 7;

        // 定义失去的生命值(不含7的)
        int lostLife = monster.attack - defend;

        //定义满血生命值
        int p = life;

        //增加判断
        if (attack - defend <= 0) {
            life -= lostBasicLife;
        } else {
            life -= lostLife + lostBasicLife;
        }
        System.out.println(name + "受到了" + (p - life) + "点伤害");
        show();
        if (life <= 0) {
            dead();
        }


    }

    public void dead() {
        System.out.println("猎人死了");
        isLive = false;
    }

    public void show() {
        System.out.println("姓名:" + name + ",武器:" + weapon + ",生命值:" +
                life + ",攻击力:" + attack + ",防御力:" + defend + ",状态:" + isLive);
    }
}