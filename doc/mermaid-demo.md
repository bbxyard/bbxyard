[TOC]

---
## Mermaind

## 流程图
### basic

```mermaid
  graph LR;
    A-->B;
    B-->D;
    A-->C;
    C-->D;
    D-->E;
```

### 节点

- 默认节点 A
- 文本节点 B[bname]
- 圆角节点 C(cname)
- 圆形节点 D((dname))
- 非对称节点 E>ename]
- 菱形节点 F{fname}

```mermaid
graph RL;
  A
  B[中括号-文本-节点]
  C(圆括号-圆角-节点)
  D((圆括号x2-圆形-节点))
  E>非对称节点]
  F{菱形节点}

  A===B
  A===C
  A===D
  D==>E
  E==>F
  F==>G
```

### 连线

- 箭头连接 A1–>B1
- 开放连接 A2—B2
- 标签连接 A3–text—B3 或者 A3—|text|B3
- 箭头标签连接 A4–text –>B4 或者 A4–>|text|B4
- 虚线开放连接 A5.-B5 或者 A5-.-B5 或者 A5..-B5
- 虚线箭头连接 A6.->B6 或者 A6-.->B6
- 标签虚线连接 A7-.text.-B7
- 标签虚线箭头连接 A8-.text.->B8
- 粗线开放连接 A9===B9
- 粗线箭头连接 A10==>B10
- 标签粗线开放连接 A11==text===B11
- 标签粗线箭头连接 A12==text==>B12

```mermaid
graph LR;
  A1 --> B1
  A2 --- B2

  a1-->B1
  a2---B2

  A31 ---|the link text| B3
  A32 --Just--- B3

  A41 -->|the link TEXT| B4
  A42 --The link TEXT--> B4

  A51-.-B5
  A52..-B5
  A53.-B5
  A6-.->B6
  A7-.text.-B7
  A8-.text.->B8
  A9===B9
  A10==>B10
  A11==text===B11
  A12==text==>B12
```

```mermaid
graph TB
  subgraph one
  a1 --> a2
  en
  subgraph two
  b2 --> b2
  end
  subgraph three
  c1 --> c2
  end
  c1 --> a2
```

**高级**
```mermaid
graph TD
      B["fa:fa-twitter for peace"]
      B-->C[fa:fa-ban forbidden]
      B-->D(fa:fa-spinner);
      B-->E(A fa:fa-camerra-retro perhaps?);
```

**样例**
```mermaid
  graph TD;
    Java --> CPP((C++))
    C# --> |MS| CPP((C++))
    CPP --> C((C))

```

---

## 时序图
```mermaid
sequenceDiagram
　　　participant Alice
　　　participant Bob
　　　Alice->John:Hello John, how are you?
　　　loop Healthcheck
　　　　　John->John:Fight against hypochondria
　　　end
　　　Note right of John:Rational thoughts <br/>prevail...
　　　John-->Alice:Great!
　　　John->Bob: How about you?
　　　Bob-->John: Jolly good!
```



## 甘特图(gantt diagram)
```mermaid
gantt
　　　dateFormat　YYYY-MM-DD
　　　title Adding GANTT diagram functionality to mermaid
　　　section A section
　　　Completed task　　:done, des1, 2014-01-06,2014-01-08
　　　Active task 　　　　:active, des2, 2014-01-09, 3d
　　　future task 　　　　:　　　  des3, after des2, 5d
　　　future task2　　　　:　　　  des4, after des3, 5d
　　　section Critical tasks
　　　Completed task in the critical line　:crit, done, 2014-01-06,24h
　　　Implement parser and json　　　　　　:crit, done, after des1, 2d
　　　Create tests for parser　　　　　　　:crit, active, 3d
　　　Future task in critical line　　　　　:crit, 5d
　　　Create tests for renderer　　　　　　:2d
　　　Add to ,mermaid　　　　　　　　　　　:1d
```