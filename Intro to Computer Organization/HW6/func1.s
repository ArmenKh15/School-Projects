	.file	"func1.c"
	.text
	.globl	func1
	.type	func1, @function
func1:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	$24, %rsp
	movl	%edi, -20(%rbp)
	movl	$0, -4(%rbp)
	cmpl	$24, -20(%rbp)
	je	.L2
	movl	$7, -4(%rbp)
.L2:
	movl	$5, -4(%rbp)
	nop
	leave
	ret
	.size	func1, .-func1
	.ident	"GCC: (GNU) 11.4.1 20231218 (Red Hat 11.4.1-3)"
	.section	.note.GNU-stack,"",@progbits
