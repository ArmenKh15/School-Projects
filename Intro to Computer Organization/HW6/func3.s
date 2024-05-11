	.file	"func3.c"
	.text
	.globl	func3
	.type	func3, @function
func3:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	$32, %rsp
	movl	%edi, -20(%rbp)
	movl	%esi, -24(%rbp)
	movl	%edx, -28(%rbp)
	movl	-20(%rbp), %eax
	movl	%eax, -4(%rbp)
	movl	-24(%rbp), %eax
	movl	%eax, -8(%rbp)
	movl	-28(%rbp), %eax
	movl	%eax, -12(%rbp)
	movl	-4(%rbp), %eax
	cmpl	-8(%rbp), %eax
	jg	.L2
	movl	-4(%rbp), %eax
	subl	%eax, -8(%rbp)
	jmp	.L5
.L2:
	movl	-8(%rbp), %eax
	cmpl	-12(%rbp), %eax
	jle	.L4
	movl	-8(%rbp), %eax
	movl	%eax, -12(%rbp)
	jmp	.L5
.L4:
	movl	-12(%rbp), %edx
	movl	-8(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -4(%rbp)
.L5:
	nop
	leave
	ret
	.size	func3, .-func3
	.ident	"GCC: (GNU) 11.4.1 20231218 (Red Hat 11.4.1-3)"
	.section	.note.GNU-stack,"",@progbits
