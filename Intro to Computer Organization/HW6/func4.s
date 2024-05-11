	.file	"func4.c"
	.text
	.globl	func4
	.type	func4, @function
func4:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	$24, %rsp
	movl	%edi, -20(%rbp)
	movl	$0, -4(%rbp)
	cmpl	$0, -20(%rbp)
	jns	.L2
	movl	$0, %eax
	jmp	.L3
.L2:
	movl	$0, -8(%rbp)
	jmp	.L4
.L5:
	movl	-8(%rbp), %eax
	addl	%eax, -4(%rbp)
	addl	$1, -8(%rbp)
.L4:
	movl	-8(%rbp), %eax
	cmpl	-20(%rbp), %eax
	jl	.L5
	movl	-4(%rbp), %eax
.L3:
	leave
	ret
	.size	func4, .-func4
	.ident	"GCC: (GNU) 11.4.1 20231218 (Red Hat 11.4.1-3)"
	.section	.note.GNU-stack,"",@progbits
