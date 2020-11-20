#include <stdio.h>
#include <stdlib.h>

#define null NULL

// 结点结构
typedef struct NodeStruct{
    int value;
    struct NodeStruct *next;
}Node;

// 链表长度
int length;
// 头结点
Node *head=NULL;

// 初始化头结点
void init(){
    head=(Node *)malloc(sizeof(Node));
    length=0;
    head->next=NULL;
    head->value=0;
}

// 头插
int insert_node(int value){
    if(head==NULL) return 0;
    Node *new_node=(Node *)malloc(sizeof(Node));
    new_node->value=value;
    new_node->next=NULL;
    length++;

    Node *p=head;
    if(p->next!=NULL){
        new_node->next=p->next;
    }
    head->next=new_node;
    return 1;
}

// 批量头插
int insert_nodes(int array[],int size){
//    int len=sizeof(*array)/sizeof(int);// array 为首元素地址。*p 为第一个元素，算出len为1
    if(head==NULL) return 0;
    int *p_a=array;
    for(int i=0;i<size;i++){
        insert_node(*(p_a+i));
    }
    return 1;
}

// 通过value，从头结点找最近的删除结点
int delete_first_ele(int value){
    if(head==NULL) return 0;
    Node *p=head;
    Node *delete_node=NULL;
    while(p){
        if(p->next->value==value){
            delete_node=p->next;
            p->next=p->next->next;
            length--;
            if(!delete_node){
                free(delete_node);// free之后，还使用引用，这种指针称野指针
                delete_node=NULL;
            }
            return 1;
        }
        p=p->next;
    }
    return 0;
}

// 通过value查询
Node* find_first_node(int value){
    if(head==NULL) return NULL;
    Node *p=head->next;
    while(p){
        if(p->value==value){
            return p;
        }
        p=p->next;
    }
    return NULL;
}

// 通过索引查询
Node* find_index_node(int index){
    if(head==NULL) return NULL;
    Node *p=head->next;
    int i=0;
    while(p){
        if(i==index){
            return p;
        }
        i++;
        p=p->next;
    }
    return NULL;
}

// 清除链表
int clear(){
    if(head==null) return 1;
    Node *p=head->next;
    Node *next=null;
    while(p){
        next=p->next;
        free(p);
        p=null;
        length--;
        if(next){
            p=next;
        }else{
            head->next=null;
            return 1;
        }
    }

    return head->next!=null?0:1;
}

// 打印链表
int print_list(){
    if(head==NULL){
        printf("head is null");
        return 0;
    }
    Node *p=head->next;
    printf("length:%d,",length);
    printf("(head) -> ");
    while(p!=NULL){
        printf("(%d)",p->value);
        if(p->next!=NULL){
            printf(" -> ");
        }
        p=p->next;
    }
    printf("\n");
    return 1;
}

int main(){
    init();
    insert_node(2);
    insert_node(3);
    int a[]={4,5,6};
    insert_nodes(a,3);
    printf("###delete_first_ele###\n");
    print_list();
    delete_first_ele(5);
    print_list();
    printf("####################\n");

    Node *find=find_first_node(2);
    printf("find_first_node value:%d,*find:%p\n",find->value,find);
    Node *index=find_index_node(0);
    printf("find_index_node value:%d\n",index->value);

    printf("###clear###\n");
    clear();
    print_list();
}




