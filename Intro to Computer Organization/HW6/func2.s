	.file	"func2.c"
	.text
	.globl	func2
	.type	func2, @function
func2:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	$24, %rsp
	movl	%edi, -20(%rbp)
	movl	%esi, -24(%rbp)
	movl	$0, -4(%rbp)
.L2:
	movl	-20(%rbp), %eax
	addl	%eax, -4(%rbp)
	subl	$1, -24(%rbp)
	cmpl	$-2, -24(%rbp)
	jge	.L2
	movl	-4(%rbp), %eax
	leave
	ret
	.size	func2, .-func2
	.ident	"GCC: (GNU) 11.4.1 20231218 (Red Hat 11.4.1-3)"
	.section	.note.GNU-stack,"",@progbits
