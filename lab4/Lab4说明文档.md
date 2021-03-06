# Lab4说明文档

姓名：邰荟媛

学号：19302010077

### 思路

哈希表记录S子串和T字符串中字符个数，通过移动S子串的左右端点遍历S寻找最小子串

（1）设置count记录子串中包含T中字符的数量，minlength记录最小子串长度，初始设为S.length+1;

（2）右端点向右移动，开始循环，直到子串右端点向右移动到字符串S的右端点——时间复杂度O(n)

a、记录当前新加入子串的字符，将哈希表内对应数量+1

b、如果该字符在字符串T中存在，且子串中该字符的数量小于等于字符串T中该字符的数量，就将count加一(若大于说明子串中包含T中重复的字符，不对其计数)；右端点向右移动一位。

c、每次循环都要判断是否子串包含T中所有元素，即count是否等于T长度。若是，将当前子串长度与minlength进行比较，若当前长度更小，则更新minlength的值；左端点向右移动，缩小子串长度，如果移动前左端点对应字符为字符串T中的字符，且移动后，子串不能包含字符串T中所有的字符，就将count减1，并回到原来的循环，继续将右端点右移直到包含T中所有的字符

（3）循环结束，若minlength未被更新过，说明字符串S中并不拥有满足条件的子串，返回一个空字符串，否则，返回满足条件的最小子串。

### 复杂度

时间复杂度是O(n)，其中n为字符串S的长度。

由于使用了哈希表保存S子串和字符串T中的字符，所以空间复杂度为O(m)，其中m为max{S中不同字符数量，T中不同字符数量}。