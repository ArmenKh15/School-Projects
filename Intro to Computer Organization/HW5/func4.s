	.file	"func4.c"
	.text
	.globl	func4
	.type	func4, @function
func4:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	$24, %rsp
	movl	%edi, -20(%rbp)
	movl	%esi, -24(%rbp)
	movl	-24(%rbp), %eax
	movl	%eax, -4(%rbp)
	movl	-20(%rbp), %eax
	addl	$7, %eax
	movl	%eax, -8(%rbp)
	movl	-8(%rbp), %eax
	orl	%eax, -24(%rbp)
	movl	-24(%rbp), %eax
	addl	%eax, -4(%rbp)
	addl	$1, -20(%rbp)
	nop
	leave
	ret
	.size	func4, .-func4
	.ident	"GCC: (GNU) 11.4.1 20231218 (Red Hat 11.4.1-3)"
	.section	.note.GNU-stack,"",@progbits
