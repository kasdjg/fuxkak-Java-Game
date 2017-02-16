class Hunter {
    int curLife;
    int maxLife;
    String name;
    String weapon;
    boolean isLive;
    int level;
    int exp;
    int attack;
    int defend;

    //增加敏捷度和躲避几率
    int minJie;//敏捷度
    //闪躲最大几率
    int hideRate;

    public Hunter(String name, String weapon) {
        this.name = name;
        this.weapon = weapon;
        curLife = 100;
        maxLife = curLife;
        isLive = true;
        level = 1;
        exp = 0;
        attack = 25;
        defend = 7;
        //为敏捷和闪躲赋值
        minJie = 10;
        hideRate = 60;
    }

    public void fight(Monster monster) {
        if (!isLive) {
            return;
        }
        if (!monster.isLive) {
            return;
        }
        System.out.println(name + "挥舞着" + weapon + "杀向了" + monster.type);
        monster.injured(this);
    }


    //躲避的方法
    public boolean hidden() {
        return GameUtil.isHide(this.minJie, this.hideRate);//可以不要this
    }

    public void injured(Monster monster) {
        //首先判断是否躲避成功,如果躲避成功则直接return,不会受伤
        if (hidden()) {
            System.out.println(name + "躲避成功!!!!!!!!!!!!!!!!!!!!!!!");
            info();
            return;
        }

        //获取丢失的生命值
        int p = curLife;
        int lostLife = GameUtil.calLostLife(monster.attack, defend);
        curLife -= lostLife;
        System.out.println(name + "受到了" + (p - curLife) + "点伤害");
        info();
        if (curLife <= 0) {
            dead();
        }
    }

    public void dead() {
        System.out.println(name + "已死亡");
        isLive = false;
    }

    public void addExp(Monster monster) {
        exp += monster.maxLife;
        System.out.println(name + "获得了" + monster.maxLife + "点经验值");
        //当前经验值>升级所需经验则升级
        //exp>needExp
        int needExp = 0;
        for (int i = 1; i <= level; i++) {
            needExp += i * 50;
        }
        if (exp >= needExp) {
            levelUp();
        }
    }

    public void levelUp() {
        System.out.println("恭喜升级!");
        level++;
        maxLife += 50;
        curLife = maxLife;
        attack += 5;
        defend += 3;
        info();
    }

    public void info() {
        System.out.println("姓名:" + name + ",当前生命值:" + curLife +
                ",武器:" + weapon + ",攻击力:" + attack +
                ",防御力:" + defend + ",当前等级:" + level + ",当前经验值:" + exp + ",是否存活:" + isLive);
    }

}