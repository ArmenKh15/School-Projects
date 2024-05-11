	.file	"func3.c"
	.text
	.globl	func3
	.type	func3, @function
func3:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movl	%esi, -8(%rbp)
	movl	%edx, -12(%rbp)
	movl	-8(%rbp), %eax
	subl	-12(%rbp), %eax
	movl	%eax, -4(%rbp)
	movl	-8(%rbp), %edx
	movl	-4(%rbp), %eax
	addl	%eax, %edx
	movl	-4(%rbp), %eax
	addl	%edx, %eax
	addl	%eax, -12(%rbp)
	movl	-4(%rbp), %eax
	sall	$3, %eax
	movl	%eax, -8(%rbp)
	movl	-8(%rbp), %eax
	leave
	ret
	.size	func3, .-func3
	.ident	"GCC: (GNU) 11.4.1 20231218 (Red Hat 11.4.1-3)"
	.section	.note.GNU-stack,"",@progbits
