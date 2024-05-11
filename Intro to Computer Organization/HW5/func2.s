	.file	"func2.c"
	.text
	.globl	func2
	.type	func2, @function
func2:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	$16, %rsp
	movl	$0, -4(%rbp)
	movl	$2, -8(%rbp)
	movl	-8(%rbp), %eax
	addl	%eax, %eax
	addl	%eax, -4(%rbp)
	movl	-4(%rbp), %eax
	movl	%eax, -8(%rbp)
	movl	-8(%rbp), %eax
	leave
	ret
	.size	func2, .-func2
	.ident	"GCC: (GNU) 11.4.1 20231218 (Red Hat 11.4.1-3)"
	.section	.note.GNU-stack,"",@progbits
