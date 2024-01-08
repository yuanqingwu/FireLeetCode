# FireLeetCode
本项目搜集了leetcode优质问题以及最优解题方法，提供一个可以直接运行的代码学习底座，使用者可以基于此项目代码进行学习，亦可基于此项目代码自行编写题解与调试。

## 语言
当前支持：Java

## 起步指南

#### 1. 代码下载：
``
git clone git@github.com:yuanqingwu/FireLeetCode.git
``

#### 2. 导入编辑器

推荐使用vscode或者其他常见java编辑器。

#### 3. 开启leetcode之旅

国内推荐站点：https://leetcode.cn/problemset/

学习建议：
1. 万事开头难，建议从简单题目起步，建立信心。放低要求，坚持下去。
2. 刚开始可以多看题解，建立初步解题思路，熟悉常见套路。
3. 后续可以按照类别进行刷题，深入理解某一类别的问题，逐个击破。
4. 有一定基础之后尝试不看题解，自行思考解题，自行优化。通过之后对比最优答案，与题解进行对比反思。（注：在提交记录界面，点击每一条柱状图都可以看到对应时间的题解，可以查看最优题解。）
   

## 项目介绍

### 数据结构

在``src/data``目录中已经封装好常见的数据结构，后续解题过程中可以直接引用。

### 问题类别

在``src/tag``目录中通过java 注解的方式罗列了常见的题目类别以及解题思路。
- 在问题类上可以通过注解标明此问题所属类别。
- 在对应问题的解法函数上可以通过注解标明不同解法的时间复杂度与空间复杂度。如此，不同解法的优劣可以一目了然。

如**动态规划**类型注解：
定义：

```
package tag;

public @interface DynamicProgramming {

    String timeComplexity() default "";

    String spaceComplexity() default "";
}
```

使用方式：
```
    @DynamicProgramming(timeComplexity = "O(N^2)", spaceComplexity = "O(N^2)")
    public String longestPalindrome(String s) {

    }
```

### 测试验证
本项目采用Junit进行答案验证。

### 项目基础结构
BaseSolution中进行了基础的封装，所有题解类继承BaseSolution类。

类命名规范：``_题目编号_题目描述.java``